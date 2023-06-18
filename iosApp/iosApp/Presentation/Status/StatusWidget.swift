//
//  StatusWidget.swift
//  iosApp
//
//  Created by Roman Makeev on 18.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Root

struct StatusWidget: View {
    let statusComponent: StatusComponent
    @ObservedObject private var model: ObservableStateFlow<StatusComponentModel>
    
    init(_ statusComponent: StatusComponent) {
        self.statusComponent = statusComponent
        model = .init(statusComponent.model)
    }
    var body: some View {
        HStack {
            HStack {
                Image(resource: \.ic_splash)
                    .resizable()
                    .frame(width: 50, height: 50, alignment: .center)
                    .aspectRatio(contentMode: .fill)
                    .background(Circle().fill(Color.gray))
                    .clipShape(Circle())
                    .padding(.trailing, 10)
                VStack(spacing: 5) {
                    HStack {
                        Text(model.value.title.localized())
                            .multilineTextAlignment(.leading)
                            .foregroundColor(.black)
                    }
                    HStack {
                        Text(model.value.status.name)
                            .multilineTextAlignment(.leading)
                            .foregroundColor(.black)
                    }
                }
                Spacer()
            }
            .padding()
            .background(Rectangle().fill(Color.white))
            .cornerRadius(10)
            .shadow(color: .gray, radius: 3, x: 2, y: 2)
        }
    }
}
