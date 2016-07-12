package hbs.eval

import scala.collection.mutable.Map

/**
 * Created by zhengzhou on 16/5/20.
 */

class Accumulate extends Function2[Map[String,BigDecimal],Map[String,BigDecimal],Map[String,BigDecimal]]{

  override def apply(v1: Map[String,BigDecimal],v2: Map[String,BigDecimal]) : Map[String,BigDecimal] = {
    v1.keys.foreach((key) =>
      if (v2.contains(key)) {
        val v = v2.get(key).get + v1.get(key).get
        v2 += (key -> v)
      } else {
        v2 += (key -> v1.get(key).get)
      })
    v2
  }

}
