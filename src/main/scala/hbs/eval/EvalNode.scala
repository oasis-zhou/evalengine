package hbs.eval

/**
 * Created by zhengzhou on 16/5/23.
 */
class EvalNode{
  var uuid: String = _
  var currentPurpose: String = _
  var factors: Map[String,Any] = Map.empty
  var result: Map[String,Any] = Map.empty
  var formulas: List[Formula] = List.empty
  var subNodes: List[EvalNode] = List.empty

  def countSubNode(node:EvalNode = this): Int = {
    def go(node:EvalNode,size:Int): Int ={
      if(node.subNodes.size == 0) 1
      else node.subNodes.map(subNode => go(subNode,1)).foldRight(size){(total,subSize) => total + subSize}
    }

   go(node,1)
  }

}
class Formula{
  var code: String = _
  var purpose: String = _
  var body: String = _
}


