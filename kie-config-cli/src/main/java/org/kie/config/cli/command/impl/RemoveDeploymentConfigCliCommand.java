/*
 * Copyright 2013 JBoss by Red Hat.
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
package org.kie.config.cli.command.impl;


import org.jboss.weld.environment.se.WeldContainer;
import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.kie.config.cli.CliContext;
import org.kie.config.cli.command.CliCommand;
import org.kie.config.cli.support.InputReader;
import org.uberfire.backend.deployment.DeploymentConfigService;

public class RemoveDeploymentConfigCliCommand implements CliCommand {

	@Override
	public String getName() {
		return "remove-deployment";
	}

	@Override
	public String execute(CliContext context) {
		StringBuffer result = new StringBuffer();
		WeldContainer container = context.getContainer();

		DeploymentConfigService deploymentConfigService = container.instance().select(DeploymentConfigService.class).get();

        InputReader input = context.getInput();
		System.out.print(">>GroupId:");
		String groupId = input.nextLine();
		
		System.out.print(">>ArtifactId:");
		String artifactId = input.nextLine();
		
		System.out.print(">>Version:");
		String version = input.nextLine();
		
		System.out.print(">>KBase name:");
		String kbase = input.nextLine();
		
		System.out.print(">>KSession name:");
		String ksession = input.nextLine();
		
		KModuleDeploymentUnit unit = new KModuleDeploymentUnit(groupId, artifactId, version, kbase, ksession);
		
		deploymentConfigService.removeDeployment(unit.getIdentifier());
		
		result.append("Deployment " + unit.getIdentifier() + " has been successfully removed");
		return result.toString();
	}

}
