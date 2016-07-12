package hbs.eval

import akka.actor.{Actor, Props}

/**
 * Created by zhengzhou on 16/4/8.
 */
class NewbizPremiumJob extends EvalJob {

  def registerStages = {
    register(context.actorOf(Props(classOf[EvalStage],"PREMIUM",new Accumulate)))
      .register(context.actorOf(Props(classOf[EvalStage],"TAXFEE",new Accumulate)))
  }
}
