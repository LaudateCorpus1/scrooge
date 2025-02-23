/**
 * Generated by Scrooge
 *   version: ?
 *   rev: ?
 *   built at: ?
 */
package com.twitter.scrooge.test.gold.thriftscala

import com.twitter.scrooge.{
  TFieldBlob,
  ThriftStruct,
  ThriftStructFieldInfo,
  ThriftStructMetaData,
  ThriftUnion,
  ThriftUnionFieldInfo,
  ValidatingThriftStruct,
  ValidatingThriftStructCodec3}
import org.apache.thrift.protocol._
import scala.collection.immutable.{Map => immutable$Map}

@javax.annotation.Generated(value = Array("com.twitter.scrooge.Compiler"))
sealed trait ResponseUnion
  extends ThriftUnion
  with ThriftStruct
  with ValidatingThriftStruct[ResponseUnion] {

  def _codec: ValidatingThriftStructCodec3[ResponseUnion] = ResponseUnion
}

object ResponseUnionAliases {

  type IdAlias = Long

  def withoutPassthroughFields_Id(obj: ResponseUnion.Id): ResponseUnion.Id = {
    ResponseUnion.Id(obj.id)
  }

  val IdKeyTypeManifest: _root_.scala.Option[Manifest[_]] = _root_.scala.None

  val IdValueTypeManifest: _root_.scala.Option[Manifest[_]] = _root_.scala.None

  type DetailsAlias = String

  def withoutPassthroughFields_Details(obj: ResponseUnion.Details): ResponseUnion.Details = {
    ResponseUnion.Details(obj.details)
  }

  val DetailsKeyTypeManifest: _root_.scala.Option[Manifest[_]] = _root_.scala.None

  val DetailsValueTypeManifest: _root_.scala.Option[Manifest[_]] = _root_.scala.None
}


@javax.annotation.Generated(value = Array("com.twitter.scrooge.Compiler"))
object ResponseUnion extends ValidatingThriftStructCodec3[ResponseUnion] {
  val Union: TStruct = new TStruct("ResponseUnion")
  val IdField: TField = new TField("id", TType.I64, 1)
  val IdFieldManifest: Manifest[Id] = manifest[Id]
  val DetailsField: TField = new TField("details", TType.STRING, 2)
  val DetailsFieldManifest: Manifest[Details] = manifest[Details]

  lazy val structAnnotations: immutable$Map[java.lang.String, java.lang.String] =
    immutable$Map[java.lang.String, java.lang.String](
        ("u.annotation", "y")
    )

  /**
   * Field information in declaration order.
   */
  lazy val fieldInfos: scala.List[ThriftUnionFieldInfo[_ <: ResponseUnion, _]] = scala.List(
    new ThriftUnionFieldInfo[Id, ResponseUnionAliases.IdAlias](
      Id.fieldInfo,
      Id.unapply
    ),
    new ThriftUnionFieldInfo[Details, ResponseUnionAliases.DetailsAlias](
      Details.fieldInfo,
      Details.unapply
    )
  )

  override lazy val metaData = ThriftStructMetaData(
    this,
    Nil,
    Nil,
    fieldInfos.asInstanceOf[Seq[ThriftUnionFieldInfo[_root_.com.twitter.scrooge.ThriftUnion with _root_.com.twitter.scrooge.ThriftStruct, _]]],
    structAnnotations)

  override def encode(_item: ResponseUnion, _oprot: TProtocol): Unit =
    _item.write(_oprot)

  override def decode(_iprot: TProtocol): ResponseUnion = {
    var _result: ResponseUnion = null
    _iprot.readStructBegin()
    val _field = _iprot.readFieldBegin()
    val _fieldType = _field.`type`
    _field.id match {
      case 1 => // id
        if (_fieldType == TType.I64) {
          _result = ResponseUnion.Id({
            _iprot.readI64()
          })
        } else TProtocolUtil.skip(_iprot, _fieldType)
      case 2 => // details
        if (_fieldType == TType.STRING) {
          _result = ResponseUnion.Details({
            _iprot.readString()
          })
        } else TProtocolUtil.skip(_iprot, _fieldType)
      case _ =>
        if (_fieldType != TType.STOP) {
          _result = UnknownUnionField(TFieldBlob.read(_field, _iprot))
        } else {
          TProtocolUtil.skip(_iprot, _fieldType)
        }
    }
    _root_.com.twitter.scrooge.internal.TProtocols.finishReadingUnion(_iprot, _fieldType, _result)

    _result
  }

  def apply(_iprot: TProtocol): ResponseUnion = decode(_iprot)

  import ResponseUnionAliases._

  def withoutPassthroughFields(struct: ResponseUnion): ResponseUnion = {
    if (struct.isInstanceOf[Id]) withoutPassthroughFields_Id(struct.asInstanceOf[Id])
    else if (struct.isInstanceOf[Details]) withoutPassthroughFields_Details(struct.asInstanceOf[Details])
    else struct //This is an UnknownUnionField, by definition passthrough
  }

  object Id extends (IdAlias => Id) {
    def withoutPassthroughFields(obj: Id): Id =
      withoutPassthroughFields_Id(obj)

    val fieldInfo: ThriftStructFieldInfo =
      new ThriftStructFieldInfo(
        IdField,
        false,
        false,
        manifest[IdAlias],
        IdKeyTypeManifest,
        IdValueTypeManifest,
        immutable$Map.empty[java.lang.String, java.lang.String],
        immutable$Map.empty[java.lang.String, java.lang.String],
        _root_.scala.None,
        _root_.scala.Option(0)
      )
  }

  case class Id(
      id: IdAlias)
    extends ResponseUnion {

    protected type ContainedType = IdAlias

    def containedValue(): ContainedType = id

    def unionStructFieldInfo: _root_.scala.Option[ThriftStructFieldInfo] =
      _root_.scala.Some(Id.fieldInfo)

    def writeFieldValue(_oprot: TProtocol): Unit = {
      val _value = id
      _oprot.writeI64(_value)
    }

    override def write(_oprot: TProtocol): Unit = {
      _oprot.writeStructBegin(Union)
        val _value = id
        _oprot.writeFieldBegin(IdField)
        _oprot.writeI64(_value)
        _oprot.writeFieldEnd()
      _oprot.writeFieldStop()
      _oprot.writeStructEnd()
    }
  }

  object Details extends (DetailsAlias => Details) {
    def withoutPassthroughFields(obj: Details): Details =
      withoutPassthroughFields_Details(obj)

    val fieldInfo: ThriftStructFieldInfo =
      new ThriftStructFieldInfo(
        DetailsField,
        false,
        false,
        manifest[DetailsAlias],
        DetailsKeyTypeManifest,
        DetailsValueTypeManifest,
        immutable$Map.empty[java.lang.String, java.lang.String],
        immutable$Map(
          ("u.field.annotation", "x")
        ),
        _root_.scala.None,
        _root_.scala.Option("empty")
      )
  }

  case class Details(
      details: DetailsAlias)
    extends ResponseUnion {

    protected type ContainedType = DetailsAlias

    def containedValue(): ContainedType = details

    def unionStructFieldInfo: _root_.scala.Option[ThriftStructFieldInfo] =
      _root_.scala.Some(Details.fieldInfo)

    def writeFieldValue(_oprot: TProtocol): Unit = {
      val _value = details
      _oprot.writeString(_value)
    }

    override def write(_oprot: TProtocol): Unit = {
      if (details eq null)
        throw new TProtocolException("Cannot write a TUnion with no set value!")
      _oprot.writeStructBegin(Union)
      if (details ne null) {
        val _value = details
        _oprot.writeFieldBegin(DetailsField)
        _oprot.writeString(_value)
        _oprot.writeFieldEnd()
      }
      _oprot.writeFieldStop()
      _oprot.writeStructEnd()
    }
  }

  case class UnknownUnionField private[ResponseUnion](
      field: TFieldBlob)
    extends ResponseUnion {

    protected type ContainedType = Unit

    def containedValue(): ContainedType = ()

    def unionStructFieldInfo: _root_.scala.Option[ThriftStructFieldInfo] = _root_.scala.None

    override def write(_oprot: TProtocol): Unit = {
      _oprot.writeStructBegin(Union)
      field.write(_oprot)
      _oprot.writeFieldStop()
      _oprot.writeStructEnd()
    }
  }

  lazy val unsafeEmpty: ResponseUnion = Id(0)

 /**
  * Checks that the struct is a valid as a new instance. If there are any missing required or
  * construction required fields, return a non-empty list.
  */
  override def validateNewInstance(
    item: ResponseUnion
  ): scala.Seq[com.twitter.scrooge.validation.Issue] = {
    validateField(item.containedValue())
  }

  /**
   * Validate that all validation annotations on the struct meet the criteria defined in the
   * corresponding [[com.twitter.scrooge.thrift_validation.ThriftConstraintValidator]].
   */
  override def validateInstanceValue(item: ResponseUnion): Set[com.twitter.scrooge.thrift_validation.ThriftValidationViolation] =
    item.unionStructFieldInfo match {
      case _root_.scala.Some(fieldInfo) =>
        validateFieldValue(fieldInfo.tfield.name, item.containedValue(), fieldInfo.fieldAnnotations, scala.None)
      case _ =>
        Set.empty
    }
}