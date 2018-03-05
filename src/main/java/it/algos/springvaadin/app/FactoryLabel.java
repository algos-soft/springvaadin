package it.algos.springvaadin.app;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.service.AHtmlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 01-mar-2018
 * Time: 07:53
 */
@SpringComponent
public class FactoryLabel implements FactoryBean<Tool> {

    @Autowired
    public AHtmlService service;

    private int factoryId;
    private int toolId;

    @Override
    public Tool getObject() throws Exception {
        Tool tool = new Tool(toolId);
        tool.setService(service);
        return tool;
    }

    @Override
    public Class<?> getObjectType() {
        return Tool.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }
}// end of class
