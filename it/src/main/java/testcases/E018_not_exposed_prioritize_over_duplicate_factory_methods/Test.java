/*
 * Copyright (c) 2018 Uber Technologies, Inc.
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
package testcases.E018_not_exposed_prioritize_over_duplicate_factory_methods;

import motif.ir.graph.errors.GraphValidationErrors;
import motif.ir.graph.errors.NotExposedError;
import motif.ir.source.base.Dependency;
import motif.ir.source.base.Type;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class Test {

    public static GraphValidationErrors errors;

    public static void run() {
        List<NotExposedError> errors = Test.errors.getNotExposedErrors();
        assertThat(errors).hasSize(1);
        NotExposedError error = errors.get(0);
        assertThat(error.getFactoryMethod()).isNotNull();
        assertThat(error.getScopeClass().getType()).isEqualTo(new Type(null, Parent.class.getName()));
        assertThat(error.getRequiredDependency().getDependency())
                .isEqualTo(new Dependency(null, new Type(null, String.class.getName()), null));
    }
}
