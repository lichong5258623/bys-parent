package com.chong.bys.core.eunm;

/**
 * 当例子用
 * @author lichong
 * @since 1.0
 */
public enum EnterwarehouseCollections {
    /**
     *  枚举信息
     */
    UN_CONFIRMED(0, "待确认"),
    TARGET_WAREHOUSE_AUDIT(1,"目的仓审核"),
    HEAD_RANGE_TRANSPORT(2,"头程运输"),
    HEAD_RANGE_ARRIVAL(3, "头程到货"),
    HEAD_RANGE_UNLOAD(4, "头程卸货"),
    HEAD_RANGE_TALLY(5, "头程理货"),
    TRANSFER(6, "转运中"),
    TAIL_RANGE_ARRIVAL(7, "尾程到货"),
    TAIL_RANGE_TALLY(8, "尾程卸货"),
    TAIL_RANGE_UNLOAD(9, "尾程理货"),
    COLLECT_GOODS_SUCCESS(10, "尾程收货"),
    PUTAWAY(11, "货品上架"),
    DISCARD(12, "废弃");
    
    
    /** 状态码*/
    private Integer code;
    
    /** 当前流程名称*/
    private String flowName;
    
    EnterwarehouseCollections(Integer code, String flowName) {
        this.code = code;
        this.flowName = flowName;
    }

    /** @return Returns the code. 
     */
    public Integer getCode() {
        return code;
    }

    /** @param code The code to set.
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /** @return Returns the flowName. 
     */
    public String getFlowName() {
        return flowName;
    }

    /** @param flowName The flowName to set.
     */
    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

}
