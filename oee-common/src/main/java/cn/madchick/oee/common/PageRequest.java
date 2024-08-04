package cn.madchick.oee.common;

/**
 * @description: 分页类
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public class PageRequest {
    /** 开始limit第1个入参 **/
    private int pageBegin = 0;
    /** 开始limit第2个入参 **/
    private int pageEnd = 0;
    /** 页数 **/
    private int page;
    /** 行数 **/
    private int rows;

    public PageRequest(){}

    /**
     * 构造函数，传入字符串类型的页数和行数
     * @param page
     * @param rows
     */
    public PageRequest(String page, String rows){this.setPage(page, rows);}

    public PageRequest(int page, int rows){this.setPage(page, rows);}

    public void setPage(String page, String rows){
        // 如果page为空，则默认为1,否则转换为int类型
        this.page = null == page ? 1 : Integer.parseInt(page);
        // 如果rows为空，则默认为1,否则转换为int类型
        this.rows = null == page ? 1 : Integer.parseInt(rows);
        // 如果page为0，则默认为1
        if(0 == this.page){this.page = 1;}
        // 计算开始和结束,用于分页查询,开始=（页数-1）*行数,结束=行数
        this.pageBegin = (this.page - 1) * this.rows;
        this.pageEnd = this.rows;
    }

    public void setPage(int page, int rows) {
        this.page = page;
        this.rows = rows;
        // 如果page为0，则默认为1
        if (0 == this.page) {this.page = 1;}
        // 计算开始和结束,用于分页查询,开始=（页数-1）*行数,结束=行数
        this.pageBegin = (this.page - 1) * this.rows;
        this.pageEnd = this.rows;
    }

    public int getPageBegin() {
        return pageBegin;
    }

    public void setPageBegin(int pageBegin) {
        this.pageBegin = pageBegin;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
