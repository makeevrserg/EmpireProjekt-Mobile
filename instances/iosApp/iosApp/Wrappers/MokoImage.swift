//
//  MokoImage.swift
//  iosApp
//
//  Created by Roman Makeev on 18.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Root

extension Image {
    init(resource: KeyPath<MR.images, ImageResource>) {
        self.init(uiImage: MR.images()[keyPath: resource].toUIImage()!)
    }
}
