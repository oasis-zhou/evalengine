package hbs.eval;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengzhou on 16/4/7.
 */
public class EvalNodeForJ {

    private String uuid;
    private Map<String,Object> factors = new HashMap<>();
    private String formula;
    private Map<String,BigDecimal> result = new HashMap<>();
    private List<EvalNodeForJ> subNodes = new ArrayList<>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Map<String, Object> getFactors() {
        return factors;
    }

    public void setFactors(Map<String, Object> factors) {
        this.factors = factors;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Map<String, BigDecimal> getResult() {
        return result;
    }

    public void setResult(Map<String, BigDecimal> result) {
        this.result = result;
    }

    public List<EvalNodeForJ> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(List<EvalNodeForJ> subNodes) {
        this.subNodes = subNodes;
    }
}
