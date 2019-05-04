package net.app.lblpack.factory.model;

/**
 * 基础用户接口
 *
 * @version 1.0.0
 */
public interface Author {
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    String getPortrait();

    void setPortrait(String portrait);
}
