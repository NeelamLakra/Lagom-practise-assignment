package com.example.hello.impl
import com.example.hello.api.{ExternalService, HellolagomService, UserData}
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.{ExecutionContext, Future}

/**
  * Implementation of the HellolagomService.
  */


class HellolagomServiceImpl(externalService: ExternalService)(implicit ec:ExecutionContext)extends HellolagomService  {


  override def greetUser(name:String) = ServiceCall { _ =>
    Future.successful("hello" +name)
  }

  override def testUser() = ServiceCall { _ =>
    val result:Future[UserData] = externalService.getUser().invoke()
    result.map(response => response)
  }

  override def age(id: Int) = ServiceCall { _ =>
    Future.successful(id)
  }

}
