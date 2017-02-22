package me.leorblx.classicnfsw.http.controller;

import me.leorblx.classicnfsw.core.Router;

public class Catalog extends Router
{
    public String productsInCategory()
    {
        return "fileref:catalog/productsInCategory_" + getParam("categoryName") + ".xml";
    }

    public String categories()
    {
        return "fileref:catalog/categories_" + getParam("categoryName") + ".xml";
    }
}
