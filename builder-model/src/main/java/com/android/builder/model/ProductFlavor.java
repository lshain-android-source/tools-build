/*
 * Copyright (C) 2013 The Android Open Source Project
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

package com.android.builder.model;

import com.android.annotations.NonNull;
import com.android.annotations.Nullable;

/**
 * a Product Flavor. This is only the configuration of the flavor.
 *
 * It does not include the sources or the dependencies. Those are available on the container
 * or in the artifact info.
 *
 * @see ProductFlavorContainer
 * @see ArtifactInfo#getDependencies()
 */
public interface ProductFlavor extends BaseConfig {

    /**
     * Returns the name of the flavor.
     *
     * @return the name of the flavor.
     */
    @NonNull
    String getName();

    /**
     * Returns the name of the product flavor. This is only the value set on this product flavor.
     * To get the final package name, use {@link ArtifactInfo#getPackageName()}.
     *
     * @return the package name.
     */
    @Nullable
    String getPackageName();

    /**
     * Returns the version code. This is only the value set on this product flavor.
     * To get the final value, use {@link Variant#getMergedFlavor()}
     *
     * @return the version code
     */
    int getVersionCode();

    /**
     * Returns the version name. This is only the value set on this product flavor.
     * To get the final value, use {@link Variant#getMergedFlavor()} as well as
     * {@link BuildType#getVersionNameSuffix()}
     *
     * @return the version name.
     */
    @Nullable
    String getVersionName();

    /**
     * Returns the minSdkVersion. This is only the value set on this product flavor.
     * TODO: make final minSdkVersion available through the model
     *
     * @return the minSdkVersion
     */
    int getMinSdkVersion();

    /**
     * Returns the targetSdkVersion. This is only the value set on this product flavor.
     * TODO: make final targetSdkVersion available through the model
     *
     * @return the targetSdkVersion
     */
    int getTargetSdkVersion();

    /**
     * Returns the renderscript target api. This is only the value set on this product flavor.
     * TODO: make final renderscript target api available through the model
     *
     * @return the renderscript target api
     */
    int getRenderscriptTargetApi();

    /**
     * Returns the test package name. This is only the value set on this product flavor.
     * To get the final value, use {@link Variant#getTestArtifactInfo()} and
     * {@link ArtifactInfo#getPackageName()}
     *
     * @return the test package name.
     */
    @Nullable
    String getTestPackageName();

    /**
     * Returns the test package name. This is only the value set on this product flavor.
     * TODO: make test instrumentation runner available through the model.
     *
     * @return the test package name.
     */
    @Nullable
    String getTestInstrumentationRunner();
}
