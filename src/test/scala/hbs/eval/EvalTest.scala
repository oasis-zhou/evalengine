package hbs.eval

import akka.actor.{Props, ActorSystem}
import org.scalatest.{FunSuite, BeforeAndAfter}

/**
 * Created by zhengzhou on 16/4/7.
 */
class EvalTest extends FunSuite with BeforeAndAfter {

  test("Test eval"){
    var n1 =  new EvalNode()
    var n2 =  new EvalNode()
    var n3 =  new EvalNode()
    n2.subNodes = List(n3);


    var root =  new EvalNode()

    root.subNodes = List(n1,n2)

    println("subnode size==" + root.countSubNode())


    // Create an Akka system
    val system = ActorSystem("SystemActor")

    // create the master
    val task = system.actorOf(Props(classOf[NewbizPremiumJob]))
//    val task2 = system.actorOf(Props(classOf[NewbizPremiumTask]))
//    val task3 = system.actorOf(Props(classOf[NewbizPremiumTask]))

    // start the calculation
    task ! Work(root)
//    task2 ! Work(root)
//    task3 ! Work(root)

    Thread.sleep(300)

    println("final result :" + root.result)
  }


}
