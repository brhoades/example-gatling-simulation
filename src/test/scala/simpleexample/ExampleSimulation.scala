package simpleexample

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ExampleSimulation extends Simulation {
  val httpConf = http
    .baseURL("https://google.com")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("Scenario Name")
    .exec(http("Main Page")
      .get("/"))
    .exec(session => {
      println(session)
      session
    })
    .exec(session => session.set("hello", "bla"))
    .exec(session => {
      println(session)
      session
    })


  setUp(scn.inject(atOnceUsers(1)).protocols(httpConf))
}
