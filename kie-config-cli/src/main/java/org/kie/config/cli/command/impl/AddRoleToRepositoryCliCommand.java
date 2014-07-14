package org.kie.config.cli.command.impl;


import org.jboss.weld.environment.se.WeldContainer;
import org.kie.config.cli.CliContext;
import org.kie.config.cli.command.CliCommand;
import org.kie.config.cli.support.InputReader;
import org.uberfire.backend.repositories.Repository;
import org.uberfire.backend.repositories.RepositoryService;

public class AddRoleToRepositoryCliCommand implements CliCommand {

	@Override
	public String getName() {
		return "add-role-repo";
	}

	@Override
	public String execute(CliContext context) {
		StringBuffer result = new StringBuffer();
		WeldContainer container = context.getContainer();

		RepositoryService repositoryService = container.instance().select(RepositoryService.class).get();

        InputReader input = context.getInput();
		System.out.print(">>Repository alias:");
		String alias = input.nextLine();
		
		Repository repo = repositoryService.getRepository(alias);
		if (repo == null) {
			return "No repository " + alias + " was found";
		}
		System.out.print(">>Security roles (comma separated list):");
		String rolesIn = input.nextLine();
		if (rolesIn.trim().length() > 0) {
			
			String[] roles = rolesIn.split(",");
			for (String role : roles) {
				repositoryService.addRole(repo, role);
				result.append("Role " + role + " added successfully to repository " + repo.getAlias() + "\n");
			}
		}
		
		return result.toString();
	}

}
