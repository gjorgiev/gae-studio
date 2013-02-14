/*
 * Copyright 2012 ArcBees Inc. All rights reserved.
 */

package com.arcbees.gaestudio.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface AppResources extends ClientBundle {
    public interface Styles extends CssResource {
        String fleft();

        String fright();

        String clear();

        String list();

        String item();

        String selected();

        String toolbar();

        String toolbarButton();

        String toolbarButtonDisabled();

        String tabs();
    }

    public interface Sprites extends CssResource {
        String topmenuBg();

        String topmenuBgHover();
    }

    public Styles styles();

    public Sprites sprites();

    @Source("images/topmenuBg.jpg")
    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.Horizontal)
    ImageResource topmenuBg();

    @Source("images/topmenuBgHover.png")
    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.Horizontal)
    ImageResource topmenuBgHover();

    @Source("images/logo.png")
    ImageResource logo();

    @Source("images/topmenuSeparator.png")
    ImageResource topmenuSeparator();

    @Source("toolbar/record.png")
    ImageResource record();

    @Source("toolbar/stop.png")
    ImageResource stop();

    @Source("toolbar/delete.png")
    ImageResource delete();

    @Source("toolbar/refresh.png")
    ImageResource refresh();

    @Source("toolbar/edit.png")
    ImageResource edit();

    @Source("toolbar/create.png")
    ImageResource create();
}