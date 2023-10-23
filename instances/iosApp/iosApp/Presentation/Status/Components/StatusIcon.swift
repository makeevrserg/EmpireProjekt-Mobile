//
//  StatusIcon.swift
//  iosApp
//
//  Created by Roman Makeev on 26.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Root

struct StatusIcon: View {
    let status: StatusComponentModelLoadingStatus
    var body: some View {
        switch status {
            
        case StatusComponentModelLoadingStatus.error:
            Image(systemName: "bolt.badge.a.fill")
                .resizable()
                .aspectRatio(1, contentMode: .fit)
                .frame(width: 50, height: 50, alignment: .center)
                .background(Circle().fill(Color.gray))
                .clipShape(Circle())
        case StatusComponentModelLoadingStatus.loading: Text("loading")
        case StatusComponentModelLoadingStatus.success: Text("Success")
        default:
            Text("NOT_IMPLEMENTED")
        }
    }
}

struct StatusIcon_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            StatusIcon(status: StatusComponentModelLoadingStatus.error)
            StatusIcon(status: StatusComponentModelLoadingStatus.success)
            StatusIcon(status: StatusComponentModelLoadingStatus.loading)
        }
    }
}
