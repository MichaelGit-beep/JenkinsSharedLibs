# JenkinsSharedLibs
Each shared library lives in its own repository. The repository has a specific structure that must be used. The first steps in using shared libraries is to set up that repository, and then configure the repository with Jenkins.

# SCM directory structure
The directory structure of a Shared Library repository is as follows:

```
(root)
+- src                     # Groovy source files
|   +- org
|       +- foo
|           +- Bar.groovy  # for org.foo.Bar class
+- vars
|   +- foo.groovy          # for global 'foo' variable
|   +- foo.txt             # help for 'foo' variable
+- resources               # resource files (external libraries only)
|   +- org
|       +- foo
|           +- bar.json    # static helper data for org.foo.Bar
```

The `src`, `resources` and `vars` directories are not required; each has its own behavior and may be included or omitted as needed. Let us look at each directory.

## src directory
The src directory uses a standard Java source directory structure. It is added to the classpath when executing Pipelines. You should rarely (preferably never) add anything to the src directory.

## vars directory
The `vars` directory contains scripts that define custom steps accessible from a Pipeline. All custom steps are defined in the root of the vars directory. The rules are:

- You can not use subfolders under var

- Each file should define one step

- The name of the file should be the name of that step, camelCased, with the .groovy suffix.

- The matching .txt file, if present, can contain documentation. This is processed through the system’s configured markup formatter.

## resources directory
The libraryResource step reads files from the resources directory and returns the content as a plain string. You can use subdirectories in the resources directory. Be sure to give each directory a name that is meaningful to you.

## Other directories
Other directories under the root of the shared library are reserved for future enhancements.



# Add Shared Lib
To configure a shared library, navigate to Manage Jenkins  Configure System on the Manage Jenkins page and go to the Global Pipeline Libraries section. When you click Add,. the configuration page for a Global Pipeline Library is displayed:

global lib config
In this unit, we only discuss using global shared libraries.

## Shared library configuration notes
- Set Default version to master to have Pipelines call custom steps from the master branch.

- If Allow default version to be overridden is enabled, a Pipeline can override this to call custom steps from other branches using the @Library annotation.

- When Load implicitly is enabled, the default branch is automatically available to all Pipelines; custom steps can also be loaded manually using the @Library annotation.

- When you select Modern SCM for the Retrieval method, the Source Code Management page pops up. Use this page to configure the repository that holds your shared library to Jenkins:


Populate these fields as you would when configuring any repository to Jenkins.

## Global libraries versus folder-level libraries
Shared libraries can be configured either as global libraries or folder-level libraries. Consider the following:

- Global libraries configured in Jenkins are considered trusted; steps from this library run outside the Groovy sandbox.

- Libraries configured at multibranch/folder level are considered not trusted and run inside the Groovy sandbox. Using libraries at multibranch/folder level incurs less risk to the Jenkins server than using libraries outside the sandbox.

You can also configure automatic shared libraries.