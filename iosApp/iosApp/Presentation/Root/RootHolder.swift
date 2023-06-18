//
//  RootHolder.swift
//  iosApp
//
//  Created by Roman Makeev on 18.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Root
import SwiftUI

final class RootHolder {
    let lifecycle: LifecycleRegistry
    let root: RootComponent
    
    init() {
        lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        let platformConfiguration = DefaultIosPlatformConfiguration()
        RootModuleCompanion.shared.platformConfiguration.initialize(value: platformConfiguration)
        root = DefaultRootComponent(
            componentContext: DefaultComponentContext(lifecycle: lifecycle),
            rootModule: RootModuleCompanion.shared,
            servicesModule: ServicesModuleCompanion.shared
        )
        lifecycle.onCreate()
    }
    
    deinit {
        lifecycle.onDestroy()
    }
}
