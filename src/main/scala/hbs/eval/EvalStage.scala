package hbs.eval

import akka.actor.{Actor, Props}

import scala.collection.mutable.Map


/**
 * Created by zhengzhou on 16/4/5.
 */
class EvalStage(purpose: String,function: Function2[Map[String,_ <: Any],Map[String,_ <: Any],Map[String,_ <: Any]]) extends Actor{

  var result: Map[String,_<: Any] = Map.empty
  var nrOfResults: Int = _
  val start: Long = System.currentTimeMillis
  var rootNode: EvalNode = _
  var nrOfNodeSize: Int = _

  val task = context.actorOf(Props[EvalTask])

  def receive = {
    case Submit(node) =>
      rootNode = node
      nrOfNodeSize = node.countSubNode()
      println("Task receive a submit message")
      task ! Work(node)

    case Result(value) =>
      nrOfResults += 1
      println("receive a message:" + value)

      result = function(value,result)

      if(nrOfResults == nrOfNodeSize){
        rootNode.result = rootNode.result ++ result
        println("result:" + result)
        context.parent ! OnComplete(result)
      }
  }

}
