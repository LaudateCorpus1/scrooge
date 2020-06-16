/**
 * Generated by Scrooge
 *   version: ?
 *   rev: ?
 *   built at: ?
 */
package com.twitter.scrooge.test.gold.thriftscala

import com.twitter.finagle.{service => ctfs}
import com.twitter.finagle.stats.{NullStatsReceiver, StatsReceiver}
import com.twitter.finagle.thrift.{Protocols, RichClientParam, ThriftClientRequest}
import com.twitter.util.Future
import org.apache.thrift.TApplicationException
import org.apache.thrift.protocol._


@javax.annotation.Generated(value = Array("com.twitter.scrooge.Compiler"))
class GoldService$FinagleClient(
    val service: com.twitter.finagle.Service[ThriftClientRequest, Array[Byte]],
    val clientParam: RichClientParam)
  extends GoldService.MethodPerEndpoint
  with GoldService.FutureIface {

  @deprecated("Use com.twitter.finagle.thrift.RichClientParam", "2017-08-16")
  def this(
    service: com.twitter.finagle.Service[ThriftClientRequest, Array[Byte]],
    protocolFactory: TProtocolFactory = Protocols.binaryFactory(),
    serviceName: String = "GoldService",
    stats: StatsReceiver = NullStatsReceiver,
    responseClassifier: ctfs.ResponseClassifier = ctfs.ResponseClassifier.Default
  ) = this(
    service,
    RichClientParam(
      protocolFactory,
      serviceName,
      clientStats = stats,
      responseClassifier = responseClassifier
    )
  )

  import GoldService._

  def serviceName: String = clientParam.serviceName

  override def asClosable: _root_.com.twitter.util.Closable = service

  private[this] def protocolFactory: TProtocolFactory = clientParam.restrictedProtocolFactory

  private[this] val tlReusableBuffer: _root_.com.twitter.scrooge.TReusableBuffer =
    clientParam.createThriftReusableBuffer()

  protected def encodeRequest(name: String, args: _root_.com.twitter.scrooge.ThriftStruct): ThriftClientRequest = {
    val memoryBuffer = tlReusableBuffer.get()
    try {
      val oprot = protocolFactory.getProtocol(memoryBuffer)

      oprot.writeMessageBegin(new TMessage(name, TMessageType.CALL, 0))
      args.write(oprot)
      oprot.writeMessageEnd()
      oprot.getTransport.flush()
      val bytes = _root_.java.util.Arrays.copyOfRange(
        memoryBuffer.getArray(),
        0,
        memoryBuffer.length()
      )
      new ThriftClientRequest(bytes, false)
    } finally {
      tlReusableBuffer.reset()
    }
  }

  protected def decodeResponse[T <: _root_.com.twitter.scrooge.ThriftStruct](
    resBytes: Array[Byte],
    codec: _root_.com.twitter.scrooge.ThriftStructCodec[T]
  ): T = {
    val iprot = protocolFactory.getProtocol(
      new org.apache.thrift.transport.TMemoryInputTransport(resBytes)
    )
    val msg = iprot.readMessageBegin()
    try {
      if (msg.`type` == TMessageType.EXCEPTION) {
        val exception = TApplicationException.readFrom(iprot)
        setServiceName(exception)
        throw exception
      } else {
        codec.decode(iprot)
      }
    } finally {
      iprot.readMessageEnd()
    }
  }

  protected def setServiceName(ex: Throwable): Throwable = {
    _root_.com.twitter.finagle.SourcedException.setServiceName(ex, serviceName)
  }

  // ----- end boilerplate.

  private[this] def stats: StatsReceiver = clientParam.clientStats
  private[this] def responseClassifier: ctfs.ResponseClassifier = clientParam.responseClassifier

  private[this] val scopedStats: StatsReceiver = if (serviceName != "") stats.scope(serviceName) else stats
  private[this] object __stats_doGreatThings {
    val RequestsCounter: _root_.com.twitter.finagle.stats.Counter = scopedStats.scope("doGreatThings").counter("requests")
    val SuccessCounter: _root_.com.twitter.finagle.stats.Counter = scopedStats.scope("doGreatThings").counter("success")
    val FailuresCounter: _root_.com.twitter.finagle.stats.Counter = scopedStats.scope("doGreatThings").counter("failures")
    val FailuresScope: StatsReceiver = scopedStats.scope("doGreatThings").scope("failures")
  }
  val doGreatThingsGoldServiceReplyDeserializer: Array[Byte] => _root_.com.twitter.util.Try[com.twitter.scrooge.test.gold.thriftscala.Response] = {
    response: Array[Byte] => {
      val result: DoGreatThings.Result = decodeResponse(response, DoGreatThings.Result)
      val firstException = result.firstException()
      if (firstException.isDefined) {
        _root_.com.twitter.util.Throw(setServiceName(firstException.get))
      } else if (result.successField.isDefined) {
        _root_.com.twitter.util.Return(result.successField.get)
      } else {
        _root_.com.twitter.util.Throw(_root_.com.twitter.scrooge.internal.ApplicationExceptions.missingResult("doGreatThings"))
      }
    }
  }
  /** Hello, I'm a comment. */
  def doGreatThings(request: com.twitter.scrooge.test.gold.thriftscala.Request): Future[com.twitter.scrooge.test.gold.thriftscala.Response] = {
    __stats_doGreatThings.RequestsCounter.incr()
    val inputArgs = DoGreatThings.Args(request)
  
    val serdeCtx = new _root_.com.twitter.finagle.thrift.ClientDeserializeCtx[com.twitter.scrooge.test.gold.thriftscala.Response](inputArgs, doGreatThingsGoldServiceReplyDeserializer)
    _root_.com.twitter.finagle.context.Contexts.local.let(
      _root_.com.twitter.finagle.thrift.ClientDeserializeCtx.Key,
      serdeCtx,
      _root_.com.twitter.finagle.thrift.Headers.Request.Key,
      _root_.com.twitter.finagle.thrift.Headers.Request.newValues
    ) {
      serdeCtx.rpcName("doGreatThings")
      val start = System.nanoTime
      val serialized = encodeRequest("doGreatThings", inputArgs)
      serdeCtx.serializationTime(System.nanoTime - start)
      this.service(serialized).flatMap { response =>
        Future.const(serdeCtx.deserialize(response))
      }.respond { response =>
        val classified = responseClassifier.applyOrElse(
          ctfs.ReqRep(inputArgs, response),
          ctfs.ResponseClassifier.Default)
        if (classified.isInstanceOf[ctfs.ResponseClass.Successful]) {
          __stats_doGreatThings.SuccessCounter.incr()
        } else if (classified.isInstanceOf[ctfs.ResponseClass.Failed]) {
          __stats_doGreatThings.FailuresCounter.incr()
          if (response.isThrow) {
            setServiceName(response.throwable)
            __stats_doGreatThings.FailuresScope.counter(
              _root_.com.twitter.util.Throwables.mkString(response.throwable): _*).incr()
          }
        } // Last ResponseClass is Ignorable, which we do not need to record
      }
    }
  }
  private[this] object __stats_noExceptionCall {
    val RequestsCounter: _root_.com.twitter.finagle.stats.Counter = scopedStats.scope("noExceptionCall").counter("requests")
    val SuccessCounter: _root_.com.twitter.finagle.stats.Counter = scopedStats.scope("noExceptionCall").counter("success")
    val FailuresCounter: _root_.com.twitter.finagle.stats.Counter = scopedStats.scope("noExceptionCall").counter("failures")
    val FailuresScope: StatsReceiver = scopedStats.scope("noExceptionCall").scope("failures")
  }
  val noExceptionCallGoldServiceReplyDeserializer: Array[Byte] => _root_.com.twitter.util.Try[com.twitter.scrooge.test.gold.thriftscala.Response] = {
    response: Array[Byte] => {
      val result: NoExceptionCall.Result = decodeResponse(response, NoExceptionCall.Result)
      val firstException = result.firstException()
      if (firstException.isDefined) {
        _root_.com.twitter.util.Throw(setServiceName(firstException.get))
      } else if (result.successField.isDefined) {
        _root_.com.twitter.util.Return(result.successField.get)
      } else {
        _root_.com.twitter.util.Throw(_root_.com.twitter.scrooge.internal.ApplicationExceptions.missingResult("noExceptionCall"))
      }
    }
  }
  
  def noExceptionCall(request: com.twitter.scrooge.test.gold.thriftscala.Request): Future[com.twitter.scrooge.test.gold.thriftscala.Response] = {
    __stats_noExceptionCall.RequestsCounter.incr()
    val inputArgs = NoExceptionCall.Args(request)
  
    val serdeCtx = new _root_.com.twitter.finagle.thrift.ClientDeserializeCtx[com.twitter.scrooge.test.gold.thriftscala.Response](inputArgs, noExceptionCallGoldServiceReplyDeserializer)
    _root_.com.twitter.finagle.context.Contexts.local.let(
      _root_.com.twitter.finagle.thrift.ClientDeserializeCtx.Key,
      serdeCtx,
      _root_.com.twitter.finagle.thrift.Headers.Request.Key,
      _root_.com.twitter.finagle.thrift.Headers.Request.newValues
    ) {
      serdeCtx.rpcName("noExceptionCall")
      val start = System.nanoTime
      val serialized = encodeRequest("noExceptionCall", inputArgs)
      serdeCtx.serializationTime(System.nanoTime - start)
      this.service(serialized).flatMap { response =>
        Future.const(serdeCtx.deserialize(response))
      }.respond { response =>
        val classified = responseClassifier.applyOrElse(
          ctfs.ReqRep(inputArgs, response),
          ctfs.ResponseClassifier.Default)
        if (classified.isInstanceOf[ctfs.ResponseClass.Successful]) {
          __stats_noExceptionCall.SuccessCounter.incr()
        } else if (classified.isInstanceOf[ctfs.ResponseClass.Failed]) {
          __stats_noExceptionCall.FailuresCounter.incr()
          if (response.isThrow) {
            setServiceName(response.throwable)
            __stats_noExceptionCall.FailuresScope.counter(
              _root_.com.twitter.util.Throwables.mkString(response.throwable): _*).incr()
          }
        } // Last ResponseClass is Ignorable, which we do not need to record
      }
    }
  }
}
