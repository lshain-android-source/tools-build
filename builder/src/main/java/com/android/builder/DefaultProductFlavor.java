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

package com.android.builder;

import com.android.annotations.NonNull;
import com.android.annotations.Nullable;
import com.android.builder.internal.BaseConfigImpl;
import com.android.builder.model.ProductFlavor;
import com.android.builder.model.SigningConfig;
import com.google.common.base.Objects;

/**
 * The configuration of a product flavor.
 *
 * This is also used to describe the default configuration of all builds, even those that
 * do not contain any flavors.
 */
public class DefaultProductFlavor extends BaseConfigImpl implements ProductFlavor {
    private static final long serialVersionUID = 1L;

    private final String mName;
    private int mMinSdkVersion = -1;
    private int mTargetSdkVersion = -1;
    private int mRenderscriptTargetApi = -1;
    private int mVersionCode = -1;
    private String mVersionName = null;
    private String mPackageName = null;
    private String mTestPackageName = null;
    private String mTestInstrumentationRunner = null;
    private SigningConfig mSigningConfig = null;

    /**
     * Creates a ProductFlavor with a given name.
     *
     * Names can be important when dealing with flavor groups.
     * @param name the name of the flavor.
     *
     * @see BuilderConstants#MAIN
     */
    public DefaultProductFlavor(@NonNull String name) {
        mName = name;
    }

    @Override
    @NonNull
    public String getName() {
        return mName;
    }

    /**
     * Sets the package name.
     *
     * @param packageName the package name
     * @return the flavor object
     */
    @NonNull
    public ProductFlavor setPackageName(String packageName) {
        mPackageName = packageName;
        return this;
    }

    @Override
    @Nullable
    public String getPackageName() {
        return mPackageName;
    }

    /**
     * Sets the version code. If the value is -1, it is considered not set.
     *
     * @param versionCode the version code
     * @return the flavor object
     */
    @NonNull
    public ProductFlavor setVersionCode(int versionCode) {
        mVersionCode = versionCode;
        return this;
    }

    @Override
    public int getVersionCode() {
        return mVersionCode;
    }

    /**
     * Sets the version name.
     *
     * @param versionName the version name
     * @return the flavor object
     */
    @NonNull
    public ProductFlavor setVersionName(String versionName) {
        mVersionName = versionName;
        return this;
    }

    @Override
    @Nullable
    public String getVersionName() {
        return mVersionName;
    }

    @NonNull
    public ProductFlavor setMinSdkVersion(int minSdkVersion) {
        mMinSdkVersion = minSdkVersion;
        return this;
    }

    @Override
    public int getMinSdkVersion() {
        return mMinSdkVersion;
    }

    @NonNull
    public ProductFlavor setTargetSdkVersion(int targetSdkVersion) {
        mTargetSdkVersion = targetSdkVersion;
        return this;
    }

    @Override
    public int getTargetSdkVersion() {
        return mTargetSdkVersion;
    }

    @Override
    public int getRenderscriptTargetApi() {
        return mRenderscriptTargetApi;
    }

    public void setRenderscriptTargetApi(int renderscriptTargetApi) {
        mRenderscriptTargetApi = renderscriptTargetApi;
    }

    @NonNull
    public ProductFlavor setTestPackageName(String testPackageName) {
        mTestPackageName = testPackageName;
        return this;
    }

    @Override
    @Nullable
    public String getTestPackageName() {
        return mTestPackageName;
    }

    @NonNull
    public ProductFlavor setTestInstrumentationRunner(String testInstrumentationRunner) {
        mTestInstrumentationRunner = testInstrumentationRunner;
        return this;
    }

    @Override
    @Nullable
    public String getTestInstrumentationRunner() {
        return mTestInstrumentationRunner;
    }

    @Nullable
    public SigningConfig getSigningConfig() {
        return mSigningConfig;
    }

    @NonNull
    public ProductFlavor setSigningConfig(SigningConfig signingConfig) {
        mSigningConfig = signingConfig;
        return this;
    }

    /**
     * Merges the flavor on top of a base platform and returns a new object with the result.
     * @param base the flavor to merge on top of
     * @return a new merged product flavor
     */
    @NonNull
    DefaultProductFlavor mergeOver(@NonNull DefaultProductFlavor base) {
        DefaultProductFlavor flavor = new DefaultProductFlavor("");

        flavor.mMinSdkVersion = chooseInt(mMinSdkVersion, base.mMinSdkVersion);
        flavor.mTargetSdkVersion = chooseInt(mTargetSdkVersion, base.mTargetSdkVersion);
        flavor.mRenderscriptTargetApi = chooseInt(mRenderscriptTargetApi,
                base.mRenderscriptTargetApi);

        flavor.mVersionCode = chooseInt(mVersionCode, base.mVersionCode);
        flavor.mVersionName = chooseString(mVersionName, base.mVersionName);

        flavor.mPackageName = chooseString(mPackageName, base.mPackageName);

        flavor.mTestPackageName = chooseString(mTestPackageName, base.mTestPackageName);
        flavor.mTestInstrumentationRunner = chooseString(mTestInstrumentationRunner,
                base.mTestInstrumentationRunner);

        flavor.mSigningConfig =
                mSigningConfig != null ? mSigningConfig : base.mSigningConfig;

        return flavor;
    }

    private int chooseInt(int overlay, int base) {
        return overlay != -1 ? overlay : base;
    }

    @Nullable
    private String chooseString(String overlay, String base) {
        return overlay != null ? overlay : base;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DefaultProductFlavor that = (DefaultProductFlavor) o;

        if (!mName.equals(that.mName)) return false;
        if (mMinSdkVersion != that.mMinSdkVersion) return false;
        if (mTargetSdkVersion != that.mTargetSdkVersion) return false;
        if (mRenderscriptTargetApi != that.mRenderscriptTargetApi) return false;
        if (mVersionCode != that.mVersionCode) return false;
        if (mPackageName != null ?
                !mPackageName.equals(that.mPackageName) :
                that.mPackageName != null)
            return false;
        if (mTestInstrumentationRunner != null ?
                !mTestInstrumentationRunner.equals(that.mTestInstrumentationRunner) :
                that.mTestInstrumentationRunner != null)
            return false;
        if (mTestPackageName != null ?
                !mTestPackageName.equals(that.mTestPackageName) : that.mTestPackageName != null)
            return false;
        if (mVersionName != null ?
                !mVersionName.equals(that.mVersionName) : that.mVersionName != null)
            return false;
        if (mSigningConfig != null ?
                !mSigningConfig.equals(that.mSigningConfig) : that.mSigningConfig != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + mName.hashCode();
        result = 31 * result + mMinSdkVersion;
        result = 31 * result + mTargetSdkVersion;
        result = 31 * result + mRenderscriptTargetApi;
        result = 31 * result + mVersionCode;
        result = 31 * result + (mVersionName != null ? mVersionName.hashCode() : 0);
        result = 31 * result + (mPackageName != null ? mPackageName.hashCode() : 0);
        result = 31 * result + (mTestPackageName != null ? mTestPackageName.hashCode() : 0);
        result = 31 * result + (mTestInstrumentationRunner != null ?
                mTestInstrumentationRunner.hashCode() : 0);
        result = 31 * result + (mSigningConfig != null ? mSigningConfig.hashCode() : 0);
        return result;
    }

    @Override
    @NonNull
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", mName)
                .add("minSdkVersion", mMinSdkVersion)
                .add("targetSdkVersion", mTargetSdkVersion)
                .add("renderscriptTargetApi", mRenderscriptTargetApi)
                .add("versionCode", mVersionCode)
                .add("versionName", mVersionName)
                .add("packageName", mPackageName)
                .add("testPackageName", mTestPackageName)
                .add("testInstrumentationRunner", mTestInstrumentationRunner)
                .add("signingConfig", mSigningConfig)
                .toString();
    }

    /*
        release signing info (keystore, key alias, passwords,...).
        native abi filter
    */

}
