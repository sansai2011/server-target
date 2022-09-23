/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.mysite.core.models;

import com.mysite.core.services.TargetCallService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TargetModel {

    @Inject
    private String mboxId;

    @Inject
    private TargetCallService targetCallService;

    @Inject
    private List<VariationModel> variation;

    public String getExperience() {
        String fragmentVariationPath = null;
        if(variation != null && variation.size() > 0) {
            for (VariationModel variationModel : variation) {
                if (variationModel.getTargetExperience().equalsIgnoreCase(targetCallService.targetResponse(mboxId))) {
                    fragmentVariationPath = variationModel.getFragmentVariationPath();
                }
            }
        }
        return fragmentVariationPath;
    }
}
