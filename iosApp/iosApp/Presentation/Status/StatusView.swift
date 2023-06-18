//
//  StatusView.swift
//  iosApp
//
//  Created by Roman Makeev on 18.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Root

struct StatusView: View {
    let rootComponent: RootComponent
    let statusComponents: [StatusComponent]
    
    init(_ rootComponent: RootComponent, _ statusComponents: [StatusComponent]) {
        self.statusComponents = statusComponents
        self.rootComponent = rootComponent
    }
    var body: some View {
        Group {
            VStack {
                
                Text(MR.strings.shared.status_title.desc().localized())
                    .font(.title)
                
                Text(MR.strings.shared.status_subtitle.desc().localized())
                    .font(.body)
                
                List(statusComponents,id: \.model.description) { statusComponent in
                    StatusWidget(statusComponent)
                }.listStyle(.inset)
            }
        }
    }
}
