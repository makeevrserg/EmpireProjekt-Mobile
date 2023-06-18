//
//  ObservableStateFlow.swift
//  iosApp
//
//  Created by Roman Makeev on 18.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Root
import SwiftUI

final class ObservableStateFlow<T: AnyObject>: ObservableObject {
    private let observableFlow: AnyStateFlow<T>
    @Published var value: T
    private var observer: Cancellable?
    
    init(_ flow: AnyStateFlow<T>) {
        observableFlow = flow
        self.value = flow.value
        observer = observableFlow.collect(
            onEach: { [weak self] value in self?.value = value }
        )
    }
    
    deinit {
        observer?.cancel()
    }
}
