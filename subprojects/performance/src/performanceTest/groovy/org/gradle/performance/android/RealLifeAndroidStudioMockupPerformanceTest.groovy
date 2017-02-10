/*
 * Copyright 2017 the original author or authors.
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

package org.gradle.performance.android

import org.gradle.performance.AbstractAndroidStudioMockupCrossVersionPerformanceTest
import spock.lang.Unroll

class RealLifeAndroidStudioMockupPerformanceTest extends AbstractAndroidStudioMockupCrossVersionPerformanceTest {

    @Unroll
    def "simulate Android Studio #template synchronization"() {
        given:

        experiment(template, "simulate Android Studio $template synchronization") {
            action('org.gradle.performance.android.SyncAction') {
                targetVersions = ["3.4-20170124101339+0000"]
                jvmArguments = customizeJvmOptions(["-Xms2g", "-Xmx2g"])
                withArguments("android.builder.sdkDownload=true")
            }
        }

        when:
        def results = performMeasurements()

        then:
        results.assertCurrentVersionHasNotRegressed()

        where:
        template << ["k9AndroidBuild", "largeAndroidBuild"]
    }

}
