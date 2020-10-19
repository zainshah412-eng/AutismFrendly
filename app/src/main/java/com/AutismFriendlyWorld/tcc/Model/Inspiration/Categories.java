package com.AutismFriendlyWorld.tcc.Model.Inspiration;

public class Categories
{
    String id;
    String name;
    String slug;
    String priority;
    String is_feature;
    String feature_title;
    String icon_map_marker;
    String color_code;
    String type;
    int status;
    String seo_title;
    String seo_description;
    String created_at;
    String updated_at;
    Translations_Inspiration_Categories translations_inspiration_categories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIs_feature() {
        return is_feature;
    }

    public void setIs_feature(String is_feature) {
        this.is_feature = is_feature;
    }

    public String getFeature_title() {
        return feature_title;
    }

    public void setFeature_title(String feature_title) {
        this.feature_title = feature_title;
    }

    public String getIcon_map_marker() {
        return icon_map_marker;
    }

    public void setIcon_map_marker(String icon_map_marker) {
        this.icon_map_marker = icon_map_marker;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSeo_title() {
        return seo_title;
    }

    public void setSeo_title(String seo_title) {
        this.seo_title = seo_title;
    }

    public String getSeo_description() {
        return seo_description;
    }

    public void setSeo_description(String seo_description) {
        this.seo_description = seo_description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Translations_Inspiration_Categories getTranslations_inspiration_categories() {
        return translations_inspiration_categories;
    }

    public void setTranslations_inspiration_categories(Translations_Inspiration_Categories translations_inspiration_categories) {
        this.translations_inspiration_categories = translations_inspiration_categories;
    }
}
