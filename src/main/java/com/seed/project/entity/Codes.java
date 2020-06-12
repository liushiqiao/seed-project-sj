package com.seed.project.entity;

import javax.persistence.*;

public class Codes {
    @Id
    private Integer codeid;

    private String codename;

    private Integer codetype;

    private String codevalue;

    private Integer byindex;

    private Integer flag;

    private Integer isuse;

    private Integer isdel;

    private String parentcode;

    private String icon;

    private String addtion1;

    private String addtion2;

    private String addtion3;

    /**
     * @return codeid
     */
    public Integer getCodeid() {
        return codeid;
    }

    /**
     * @param codeid
     */
    public void setCodeid(Integer codeid) {
        this.codeid = codeid;
    }

    /**
     * @return codename
     */
    public String getCodename() {
        return codename;
    }

    /**
     * @param codename
     */
    public void setCodename(String codename) {
        this.codename = codename;
    }

    /**
     * @return codetype
     */
    public Integer getCodetype() {
        return codetype;
    }

    /**
     * @param codetype
     */
    public void setCodetype(Integer codetype) {
        this.codetype = codetype;
    }

    /**
     * @return codevalue
     */
    public String getCodevalue() {
        return codevalue;
    }

    /**
     * @param codevalue
     */
    public void setCodevalue(String codevalue) {
        this.codevalue = codevalue;
    }

    /**
     * @return byindex
     */
    public Integer getByindex() {
        return byindex;
    }

    /**
     * @param byindex
     */
    public void setByindex(Integer byindex) {
        this.byindex = byindex;
    }

    /**
     * @return flag
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * @param flag
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * @return isuse
     */
    public Integer getIsuse() {
        return isuse;
    }

    /**
     * @param isuse
     */
    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

    /**
     * @return isdel
     */
    public Integer getIsdel() {
        return isdel;
    }

    /**
     * @param isdel
     */
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    /**
     * @return parentcode
     */
    public String getParentcode() {
        return parentcode;
    }

    /**
     * @param parentcode
     */
    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return addtion1
     */
    public String getAddtion1() {
        return addtion1;
    }

    /**
     * @param addtion1
     */
    public void setAddtion1(String addtion1) {
        this.addtion1 = addtion1;
    }

    /**
     * @return addtion2
     */
    public String getAddtion2() {
        return addtion2;
    }

    /**
     * @param addtion2
     */
    public void setAddtion2(String addtion2) {
        this.addtion2 = addtion2;
    }

    /**
     * @return addtion3
     */
    public String getAddtion3() {
        return addtion3;
    }

    /**
     * @param addtion3
     */
    public void setAddtion3(String addtion3) {
        this.addtion3 = addtion3;
    }
}