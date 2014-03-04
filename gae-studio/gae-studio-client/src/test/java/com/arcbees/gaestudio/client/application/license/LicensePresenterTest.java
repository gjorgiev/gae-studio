/**
 * Copyright (c) 2014 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.client.application.license;

import javax.inject.Inject;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;

import com.arcbees.gaestudio.client.util.AsyncMockStubber;
import com.arcbees.gaestudio.client.util.CurrentUser;
import com.arcbees.gaestudio.shared.auth.User;
import com.google.api.client.http.HttpStatusCodes;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.rest.shared.RestDispatch;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JukitoRunner.class)
public class LicensePresenterTest {
    public static class MyModule extends JukitoModule {
        @Override
        protected void configureTest() {
            forceMock(CurrentUser.class);
        }
    }

    @Inject
    LicensePresenter presenter;

    @Test
    public void onReveal_licenseCheckReturns403_noErrorMessages(LicensePresenter.MyView view,
                                                                RestDispatch dispatch,
                                                                CurrentUser currentUser) {
        //given
        makeDispatcherReturn(dispatch, HttpStatusCodes.STATUS_CODE_FORBIDDEN);
        when(currentUser.getUser()).thenReturn(mock(User.class));

        //when
        presenter.onReveal();

        //then
        verify(view, never()).showMessage(anyString());
    }

    private void makeDispatcherReturn(RestDispatch dispatch, int statuscode) {
        Response response = buildResponse(statuscode);
        AsyncMockStubber.callResponseWith(response).when(dispatch).execute(Matchers.<RestAction>any(),
                Matchers.<AsyncCallback>any());
    }

    private Response buildResponse(int statuscode) {
        Response response = mock(Response.class);
        when(response.getStatusCode()).thenReturn(statuscode);

        return response;
    }
}
