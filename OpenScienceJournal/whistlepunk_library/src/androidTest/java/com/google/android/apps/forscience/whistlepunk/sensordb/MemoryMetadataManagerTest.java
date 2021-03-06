/*
 *  Copyright 2016 Google Inc. All Rights Reserved.
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

package com.google.android.apps.forscience.whistlepunk.sensordb;

import android.test.AndroidTestCase;

import com.google.android.apps.forscience.whistlepunk.metadata.Experiment;
import com.google.android.apps.forscience.whistlepunk.metadata.Project;
import com.google.common.collect.Lists;

public class MemoryMetadataManagerTest extends AndroidTestCase {
    public void testLastUsed() {
        MemoryMetadataManager mmm = new MemoryMetadataManager();
        Project project = mmm.newProject();
        assertEquals(project.getProjectId(), mmm.getLastUsedProject().getProjectId());
    }

    public void testExperimentOrdering() {
        MemoryMetadataManager mmm = new MemoryMetadataManager();
        Project project = mmm.newProject();
        Experiment e1 = mmm.newExperiment(project, 1, "e1");
        Experiment e2 = mmm.newExperiment(project, 2, "e2");
        Experiment e3 = mmm.newExperiment(project, 3, "e3");
        assertEquals(Lists.newArrayList(e3, e2, e1), mmm.getExperimentsForProject(project, false));
        mmm.updateLastUsedExperiment(e2);
        assertEquals(Lists.newArrayList(e2, e3, e1), mmm.getExperimentsForProject(project, false));
    }
}