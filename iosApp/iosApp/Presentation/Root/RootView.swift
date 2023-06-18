//
//  RootView.swift
//  iosApp
//
//  Created by Roman Makeev on 18.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Root
import SwiftUI

struct RootView: View {
    private let root: RootComponent
    @ObservedObject
    private var childStack: ObservableValue<ChildStack<AnyObject, DefaultRootComponentConfiguration>>
    
    private var stack: ChildStack<AnyObject, DefaultRootComponentConfiguration> { childStack.value }
    
    init(_ root: RootComponent) {
        self.root = root
        childStack = .init(root.childStack)
    }
    
    var body: some View {
        StackView(
            stackValue: childStack,
            getTitle: { _ in "" },
            onBack: root.pop,
            childContent: { configuration in
                ChildView(root: root, child: configuration)
            }
        )
        .preferredColorScheme(.dark)
        
    }
}

private struct ChildView: View {
    let root: RootComponent
    let child: DefaultRootComponentConfiguration
    
    var body: some View {
        switch child {
        case let child as DefaultRootComponentConfigurationSplash:
            SplashView(root, child.splashComponent)
        case let child as DefaultRootComponentConfigurationStatus:
            StatusView(root, [child.ainteractiveStatusComponent,
                              child.alearnerDevStatusComponent,
                              child.alearnerProdStatusComponent,
                              child.esmpStatusComponent])
        default:
            EmptyView()
        }
    }
}
