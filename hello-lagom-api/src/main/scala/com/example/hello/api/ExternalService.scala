package com.example.hello.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

trait ExternalService extends Service {

  def getUser() : ServiceCall[NotUsed,UserData]

  override final def descriptor: Descriptor = {
    import Service._
    named("json_placeholder")
      .withCalls(
          pathCall("/posts/1",getUser _).withAutoAcl(true)
      ).withAutoAcl(true)
  }
}