package cn.nesc.general.authcenter.model;

import java.io.Serializable;
import java.util.Date;

public class TmMenuPO implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_menu.menu_id
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    private Integer menuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_menu.path
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    private String path;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_menu.title
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_menu.icon
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    private String icon;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_menu.func_order
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    private Integer funcOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_menu.father_id
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    private Integer fatherId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_menu.is_delete
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    private Integer isDelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_menu.create_by
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_menu.create_date
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_menu.update_by
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    private String updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_menu.update_date
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tm_menu
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_menu.menu_id
     *
     * @return the value of tm_menu.menu_id
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_menu.menu_id
     *
     * @param menuId the value for tm_menu.menu_id
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_menu.path
     *
     * @return the value of tm_menu.path
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_menu.path
     *
     * @param path the value for tm_menu.path
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_menu.title
     *
     * @return the value of tm_menu.title
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_menu.title
     *
     * @param title the value for tm_menu.title
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_menu.icon
     *
     * @return the value of tm_menu.icon
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_menu.icon
     *
     * @param icon the value for tm_menu.icon
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_menu.func_order
     *
     * @return the value of tm_menu.func_order
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public Integer getFuncOrder() {
        return funcOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_menu.func_order
     *
     * @param funcOrder the value for tm_menu.func_order
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public void setFuncOrder(Integer funcOrder) {
        this.funcOrder = funcOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_menu.father_id
     *
     * @return the value of tm_menu.father_id
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public Integer getFatherId() {
        return fatherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_menu.father_id
     *
     * @param fatherId the value for tm_menu.father_id
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_menu.is_delete
     *
     * @return the value of tm_menu.is_delete
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_menu.is_delete
     *
     * @param isDelete the value for tm_menu.is_delete
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_menu.create_by
     *
     * @return the value of tm_menu.create_by
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_menu.create_by
     *
     * @param createBy the value for tm_menu.create_by
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_menu.create_date
     *
     * @return the value of tm_menu.create_date
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_menu.create_date
     *
     * @param createDate the value for tm_menu.create_date
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_menu.update_by
     *
     * @return the value of tm_menu.update_by
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_menu.update_by
     *
     * @param updateBy the value for tm_menu.update_by
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_menu.update_date
     *
     * @return the value of tm_menu.update_date
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_menu.update_date
     *
     * @param updateDate the value for tm_menu.update_date
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}