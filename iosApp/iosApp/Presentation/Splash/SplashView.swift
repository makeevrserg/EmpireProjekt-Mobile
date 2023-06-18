//
//  SplashView.swift
//  iosApp
//
//  Created by Roman Makeev on 18.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Root

struct SplashView: View {
    let rootComponent: RootComponent
    let splashComponent: SplashComponent
    
    init(_ rootComponent: RootComponent, _ splashComponent: SplashComponent) {
        self.splashComponent = splashComponent
        self.rootComponent = rootComponent
        UIScrollView.appearance().contentInsetAdjustmentBehavior = .never
    }
    var body: some View {
        Group {
            VStack {
                Image(resource: \.ic_splash)
                    .resizable()
                    .frame(width: 128.0, height: 128.0, alignment: .center)
                    .aspectRatio(contentMode: .fill)
                ProgressView()
                    .padding(EdgeInsets(top: 16, leading: 0, bottom: 16, trailing: 0))
            }
        }.onReceive(createPublisher(splashComponent.screenChannelFlow)) { label in
            switch label {
            case is SplashComponentLabelInitialLaunch:
                rootComponent.replaceAll(screen: RootComponentChildStatus())
            default: break
            }
        }
    }
}
