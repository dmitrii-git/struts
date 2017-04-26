/*
 * Copyright 2002-2006,2009 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.opensymphony.xwork2;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;

import java.util.Arrays;
import java.util.Locale;

/**
 * Default implementation of {@link LocaleProvider}
 */
public class DefaultLocaleProvider implements LocaleProvider {

    private final static Logger LOG = LogManager.getLogger(DefaultLocaleProvider.class);

    @Override
    public Locale getLocale() {
        ActionContext ctx = ActionContext.getContext();
        if (ctx != null) {
            return ctx.getLocale();
        } else {
            LOG.debug("Action context not initialized");
            return null;
        }
    }

    @Override
    public boolean isValidLocaleString(String localeStr) {
        Locale locale = null;
        try {
            locale = LocaleUtils.toLocale(localeStr);
        } catch (IllegalArgumentException e) {
            LOG.warn(new ParameterizedMessage("Cannot convert [{}] to proper locale", localeStr, e));
        }
        return isValidLocale(locale);
    }

    @Override
    public boolean isValidLocale(Locale locale) {
        return locale != null && Arrays.asList(Locale.getAvailableLocales()).contains(locale);
    }
}
