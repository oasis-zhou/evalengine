package hbs.eval

import akka.actor.{Props, Actor}
import akka.routing.RoundRobinPool
import scala.collection.mutable.Map
import scala.collection.JavaConversions._


/**
 * Created by zhengzhou on 16/4/5.
 */
class EvalTask extends Actor {

  def eval(node:EvalNode): Map[String,_ <: Any] = {
    var result = evalSelf(node)

    for (subEvalNode <- node.subNodes){
      subEvalNode.factors = subEvalNode.factors ++ result
      val subWorker = context.actorOf(Props[EvalTask])
      subWorker ! Work(subEvalNode)
    }

    result
  }

  def evalSelf(evalNode:EvalNode): Map[String,_ <: Any] = {
    var result = Map("premium" -> BigDecimal(100.00))
    val fastors = evalNode.factors
    evalNode.result = evalNode.result ++ result


    result
  }

  def receive = {
    case Work(evalNode) =>
      println("task work message")
      sender ! Result(eval(evalNode)) // perform the work
    case Result(value) =>
      println("task result message,parent:" + context.parent.path)
      context.parent ! Result(value)
  }
}
