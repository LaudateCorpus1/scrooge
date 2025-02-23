package com.twitter.scrooge.java_generator

import com.twitter.scrooge.ast.{Function => TFunction, _}
import com.twitter.scrooge.ast.Field
import com.twitter.scrooge.ast.SimpleID
import com.twitter.scrooge.ast.Struct
import com.google.common.base
import com.twitter.scrooge.backend.ServiceOption

class FunctionController(
  function: TFunction,
  serviceOptions: Set[ServiceOption],
  generator: ApacheJavaGenerator,
  ns: Option[Identifier],
  serviceName: String)
    extends BaseController(generator, ns) {
  val return_type: FieldTypeController = new FieldTypeController(function.funcType, generator)
  val name: String = function.funcName.name
  val service_name: String = serviceName
  val argument_list: String = function.args map { a =>
    a.sid.name
  } mkString ", "
  val argument_list_with_types: String = function.args map { a =>
    generator.typeName(a.fieldType) + " " + a.sid.name
  } mkString ", "
  val argument_list_with_args: String = function.args map { a =>
    "args." + a.sid.name
  } mkString ", "
  val has_args: Boolean = function.args.size > 0
  val fields: Seq[FieldController] = function.args map { a =>
    new FieldController(a, generator, ns)
  }
  val exceptions_string: String = {
    val exceptions = function.throws map (a => generator.typeName(a.fieldType))
    if (exceptions.size > 0) {
      exceptions.mkString(", ") + ", "
    } else {
      ""
    }
  }

  val exceptions: Seq[FieldController] = function.throws.zipWithIndex map {
    case (e, i) =>
      new FieldController(e, generator, ns) {
        val first = i == 0
      }
  }

  val has_exceptions: Boolean = exceptions.size > 0

  val is_oneway: Boolean = function.funcType == OnewayVoid
  val is_oneway_or_void: Boolean = is_oneway || return_type.is_void

  // thrift validations utilities
  val validationFields: Seq[Field] = function.args.filter(FieldController.hasValidationAnnotation)
  val numArgWithValidations: Int = validationFields.size
  val argsWithValidations: Seq[FieldController] =
    validationFields.zipWithIndex.map {
      case (arg, index) =>
        new FieldController(arg, generator, ns) {
          val firstArg: Boolean = index == 0
          val middleArg: Boolean = index > 0 && index < numArgWithValidations - 1
          val lastArg: Boolean = index == numArgWithValidations - 1
          // need to have `oneArg` inside `argsWithValidations` to be able to access
          // variables defined in `FieldController` upon evaluating `oneArg` in template
          val oneArg: Boolean = numArgWithValidations == 1
        }
    }
  val hasValidationAnnotation: Boolean = argsWithValidations.nonEmpty
  val violationReturningFuncName: String =
    function.funcName.toTitleCase.prepend("violationReturning").name

  def i_if_has_exceptions: base.Function[String, String] = newHelper { input =>
    if (exceptions.size > 0) indent(input, 2, false) else input
  }

  def arg_struct: String = {
    val args = function.args map { a =>
      val requiredness =
        if (a.requiredness.isRequired) Requiredness.Required else Requiredness.Default
      Field(
        index = a.index,
        sid = a.sid,
        originalName = a.originalName,
        fieldType = a.fieldType,
        default = a.default,
        requiredness = requiredness,
        fieldAnnotations = a.fieldAnnotations,
        hasValidationAnnotation = a.fieldAnnotations.keySet.exists(_.startsWith("validation."))
      )
    }
    val structName = function.funcName.name + "_args"
    val struct = Struct(SimpleID(structName), structName, args, function.docstring, Map.empty)
    val controller = new StructController(struct, serviceOptions, true, generator, ns)
    generator.renderMustache("struct_inner.mustache", controller)
  }

  def result_struct: String = {
    val fields = (if (function.funcType == Void) {
                    Seq()
                  } else {
                    val fieldType = function.funcType.asInstanceOf[FieldType]
                    Seq(
                      Field(
                        0,
                        SimpleID("success"),
                        "success",
                        fieldType,
                        None,
                        Requiredness.Default
                      )
                    )
                  }) ++ function.throws
    val struct = Struct(
      SimpleID(function.funcName.name + "_result"),
      function.originalName + "_result",
      fields,
      None,
      Map.empty
    )
    val controller =
      new StructController(struct, serviceOptions, true, generator, ns, is_result = true)
    generator.renderMustache("struct_inner.mustache", controller)
  }
}
