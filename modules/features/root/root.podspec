Pod::Spec.new do |spec|
    spec.name                     = 'root'
    spec.version                  = '0.2.4'
    spec.homepage                 = 'https://empireprojekt.ru'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'EmpireProjekt mobile application'
    spec.vendored_frameworks      = 'build/cocoapods/framework/Root.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '16.0'
                
                
    if !Dir.exist?('build/cocoapods/framework/Root.framework') || Dir.empty?('build/cocoapods/framework/Root.framework')
        raise "

        Kotlin framework 'Root' doesn't exist yet, so a proper Xcode project can't be generated.
        'pod install' should be executed after running ':generateDummyFramework' Gradle task:

            ./gradlew :modules:features:root:generateDummyFramework

        Alternatively, proper pod installation is performed during Gradle sync in the IDE (if Podfile location is set)"
    end
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':modules:features:root',
        'PRODUCT_MODULE_NAME' => 'Root',
    }
                
    spec.script_phases = [
        {
            :name => 'Build root',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../../../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end