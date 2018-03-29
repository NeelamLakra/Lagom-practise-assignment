package com.example.hello.api
import akka.{NotUsed}
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}

object HellolagomService  {
  val TOPIC_NAME = "greetings"
}

/**
  * The hello-lagom service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the HellolagomService.
  */


trait HellolagomService extends Service {

  /**
    * Example: curl http://localhost:9000/api/hello/Alice
    */
  def age(id:Int):ServiceCall[NotUsed,Int]
  def greetUser(name:String) :ServiceCall[NotUsed,String]
  def testUser() :ServiceCall[NotUsed,UserData]

  /**
    * Example: curl -H "Content-Type: application/json" -X POST -d '{"message":
    * "Hi"}' http://localhost:9000/api/hello/Alice
    */
  /**
    * This gets published to Kafka.
    */

  override final def descriptor = {
    import Service._
    // @formatter:off
    named("hello-lagom")
      .withCalls(
        pathCall("/api/age/:id",age _),
        pathCall("/api/user", testUser _)
      )
      .withAutoAcl(true)
    // @formatter:on
  }
}

case class UserData(userId:Int,id:Int,title:String,body:String)
object UserData {
  implicit val format: Format[UserData] = Json.format[UserData]

}
