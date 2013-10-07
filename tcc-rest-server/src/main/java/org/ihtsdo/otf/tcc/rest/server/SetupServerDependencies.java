/*
 * Copyright 2013 International Health Terminology Standards Development Organisation.
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
package org.ihtsdo.otf.tcc.rest.server;

import java.io.File;
import org.apache.maven.cli.MavenCli;

/**
 *
 * @author kec
 */
public class SetupServerDependencies {
    public static void main(String[] args) {
        MavenCli cli = new MavenCli();
        
        if (args == null || args.length == 0) {
            args = new String[]{"clean", "install"};
        }
 
        String workingDirectory = new File(".").getAbsolutePath();
        
        int result = cli.doMain(args, workingDirectory, System.out, System.out);        
        
        System.out.println("result: " + result);
        
        
    }
}
