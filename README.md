# CoreMedia LiveContext 3 for Custom Commerce Systems

The main part of this repository is the Wiki describing the necessary steps to
integrate CoreMedia LiveContext 3 with your custom commerce system.

As part of this scenario and to present a generic manual it turned out to be
helpful to have a mock product catalog implementation in place ready to use,
to check if other parts are set up correctly.

So the code part of this repository is no full CoreMedia LiveContext 3 integration
but leaves out the presenation layer in the commerce system and parts of its
CoreMedia LiveContext 3 counterpart, since these - Adapter and at least a Theme 
- have to be implemented following the details of your custom commerce system
which can't be impemented generically but just described in the Wiki.

## Elements of the Code Repository

* Mock Catalog Module
* 2 Extensions to the Theme
* CoreMedia Mock Catalog Extension
* CoreMedia Theme
* Additions to the Workspace
* patch-script to prepare the CoreMedia side

## Add Extension to the Workspace

You will have received access to a stock CoreMedia platform workspace with
the Live Context base modules available.

Add this repository's `mock-catalog` folder to the `modules/extensions` area of this 
workspace and list it in the `modules/extensions/pom.xml`.

```
    <module>taxonomy</module>
    <module>validators</module>
    <module>mock-catalog</module>
  </modules>
```

Add a reference to the `bom` sub-module to the root `pom.xml` of the workspace.

```
      <!-- custom extensions -->
      <dependency>
        <groupId>${project.groupId}</groupId>
        <artifactId>mock-catalog-bom</artifactId>
        <version>${project.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!-- custom extensions end -->
```

After this step the activation of the extension will be in effect after the
usual

```
mvn com.coremedia.maven:coremedia-blueprint-maven-plugin:update-extensions
```

step.

## Copy theme to Workspace

The directory `theme` is supposed to be copied as `mock-minimal-theme` to the 
subdirectory `modules/frontend/themes`. To be able to prepare the upload
artifact for this theme, you will have to extend the grunt tooling of
the workspace with one file from the latest development: `sass.js`. It
needs to be placed in 

```
modules/frontend/lib/tools/grunt/configs/themes/sass.js
```

## Cosmetics

For consistency reasons you might decide to move the dependency management part
of the root `pom.xml` of the mock-catalog extension to the root `pom.xml` of the
workspace.
