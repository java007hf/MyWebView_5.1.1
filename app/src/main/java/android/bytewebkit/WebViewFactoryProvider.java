/*
 * Copyright (C) 2012 The Android Open Source Project
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

package android.bytewebkit;

import android.annotation.SystemApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * This is the main entry-point into the WebView back end implementations, which the WebView
 * proxy class uses to instantiate all the other objects as needed. The backend must provide an
 * implementation of this interface, and make it available to the WebView via mechanism TBD.
 * @hide
 */
@SystemApi
public interface WebViewFactoryProvider {
    /**
     * This Interface provides glue for implementing the backend of WebView static methods which
     * cannot be implemented in-situ in the proxy class.
     */
    interface Statics {
        /**
         * Implements the API method:
         * {@link android.bytewebkit.WebView#findAddress(String)}
         */
        String findAddress(String addr);

        /**
         * Implements the API method:
         * {@link WebSettings#getDefaultUserAgent(Context) }
         */
        String getDefaultUserAgent(Context context);

        /**
         * Used for tests only.
         */
         void freeMemoryForTests();

        /**
         * Implements the API method:
         * {@link android.bytewebkit.WebView#setWebContentsDebuggingEnabled(boolean) }
         */
        void setWebContentsDebuggingEnabled(boolean enable);

        /**
         * Implements the API method:
         * {@link android.bytewebkit.WebView#clearClientCertPreferences(Runnable) }
         */
        void clearClientCertPreferences(Runnable onCleared);

        /**
         * Implements the API method:
         * {@link android.bytewebkit.WebView#setSlowWholeDocumentDrawEnabled(boolean) }
         */
        void enableSlowWholeDocumentDraw();

        /**
         * Implement the API method
         * {@link WebChromeClient.FileChooserParams#parseResult(int, Intent)}
         */
        Uri[] parseFileChooserResult(int resultCode, Intent intent);
    }

    Statics getStatics();

    /**
     * Construct a new WebViewProvider.
     * @param webView the WebView instance bound to this implementation instance. Note it will not
     * necessarily be fully constructed at the point of this call: defer real initialization to
     * WebViewProvider.init().
     * @param privateAccess provides access into WebView internal methods.
     */
    WebViewProvider createWebView(android.bytewebkit.WebView webView, android.bytewebkit.WebView.PrivateAccess privateAccess);

    /**
     * Gets the singleton GeolocationPermissions instance for this WebView implementation. The
     * implementation must return the same instance on subsequent calls.
     * @return the single GeolocationPermissions instance.
     */
    android.bytewebkit.GeolocationPermissions getGeolocationPermissions();

    /**
     * Gets the singleton CookieManager instance for this WebView implementation. The
     * implementation must return the same instance on subsequent calls.
     *
     * @return the singleton CookieManager instance
     */
    android.bytewebkit.CookieManager getCookieManager();

    /**
     * Gets the singleton WebIconDatabase instance for this WebView implementation. The
     * implementation must return the same instance on subsequent calls.
     *
     * @return the singleton WebIconDatabase instance
     */
    android.bytewebkit.WebIconDatabase getWebIconDatabase();

    /**
     * Gets the singleton WebStorage instance for this WebView implementation. The
     * implementation must return the same instance on subsequent calls.
     *
     * @return the singleton WebStorage instance
     */
    android.bytewebkit.WebStorage getWebStorage();

    /**
     * Gets the singleton WebViewDatabase instance for this WebView implementation. The
     * implementation must return the same instance on subsequent calls.
     *
     * @return the singleton WebViewDatabase instance
     */
    android.bytewebkit.WebViewDatabase getWebViewDatabase(Context context);
}
