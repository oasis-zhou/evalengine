package hbs.eval

import akka.actor.{ActorRef, Props, Actor}

/**
 * Created by zhengzhou on 16/4/7.
 */
trait EvalJob extends Actor{
  var stages: List[ActorRef] = List.empty
  var nrOfStates: Int = _
  var node: EvalNode = _

  def register(stage:ActorRef): EvalJob = {
    stages = stage :: stages
    this
  }

  def receive = {
    case Work(evalNode) =>
      node = evalNode
      registerStages
      println("Submit work")
      stages.head ! Submit(evalNode)

    case OnComplete(result) =>
      nrOfStates += 1
      println("\n\tEvalutation result: \t\t%s".format(result))
      if(nrOfStates < stages.size){
        stages(nrOfStates) ! Submit(node)
      }
  }

  def registerStages

}
