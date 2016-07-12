package hbs.eval

import scala.collection.mutable.Map
/**
 * Created by zhengzhou on 16/4/5.
 */
sealed trait EvalMessage
case class Submit(evalNode:EvalNode) extends EvalMessage
case class Work(evalNode:EvalNode) extends EvalMessage
case class Result(value: Map[String,_ <: Any]) extends EvalMessage
case class OnComplete[+T](value: Map[String,_ <: Any])
