package cn.itcast.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbContent implements Serializable{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content.category_id
     *
     * @mbg.generated
     */
    private Long categoryId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content.title
     *
     * @mbg.generated
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content.sub_title
     *
     * @mbg.generated
     */
    private String subTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content.title_desc
     *
     * @mbg.generated
     */
    private String titleDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content.url
     *
     * @mbg.generated
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content.pic
     *
     * @mbg.generated
     */
    private String pic;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content.pic2
     *
     * @mbg.generated
     */
    private String pic2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content.created
     *
     * @mbg.generated
     */
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content.updated
     *
     * @mbg.generated
     */
    private Date updated;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content.id
     *
     * @return the value of tb_content.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content.id
     *
     * @param id the value for tb_content.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content.category_id
     *
     * @return the value of tb_content.category_id
     *
     * @mbg.generated
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content.category_id
     *
     * @param categoryId the value for tb_content.category_id
     *
     * @mbg.generated
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content.title
     *
     * @return the value of tb_content.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content.title
     *
     * @param title the value for tb_content.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content.sub_title
     *
     * @return the value of tb_content.sub_title
     *
     * @mbg.generated
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content.sub_title
     *
     * @param subTitle the value for tb_content.sub_title
     *
     * @mbg.generated
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content.title_desc
     *
     * @return the value of tb_content.title_desc
     *
     * @mbg.generated
     */
    public String getTitleDesc() {
        return titleDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content.title_desc
     *
     * @param titleDesc the value for tb_content.title_desc
     *
     * @mbg.generated
     */
    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc == null ? null : titleDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content.url
     *
     * @return the value of tb_content.url
     *
     * @mbg.generated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content.url
     *
     * @param url the value for tb_content.url
     *
     * @mbg.generated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content.pic
     *
     * @return the value of tb_content.pic
     *
     * @mbg.generated
     */
    public String getPic() {
        return pic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content.pic
     *
     * @param pic the value for tb_content.pic
     *
     * @mbg.generated
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content.pic2
     *
     * @return the value of tb_content.pic2
     *
     * @mbg.generated
     */
    public String getPic2() {
        return pic2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content.pic2
     *
     * @param pic2 the value for tb_content.pic2
     *
     * @mbg.generated
     */
    public void setPic2(String pic2) {
        this.pic2 = pic2 == null ? null : pic2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content.created
     *
     * @return the value of tb_content.created
     *
     * @mbg.generated
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content.created
     *
     * @param created the value for tb_content.created
     *
     * @mbg.generated
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content.updated
     *
     * @return the value of tb_content.updated
     *
     * @mbg.generated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content.updated
     *
     * @param updated the value for tb_content.updated
     *
     * @mbg.generated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content.content
     *
     * @return the value of tb_content.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content.content
     *
     * @param content the value for tb_content.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}