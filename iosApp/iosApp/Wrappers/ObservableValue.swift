//
//  ObservableValue.swift
//  iosApp
//
//  Created by Roman Makeev on 18.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Root
import SwiftUI

final class ObservableValue<T: AnyObject>: ObservableObject {
    private let observableValue: Value<T>
    @Published var value: T
    private var observer: ((T) -> Void)?
    
    init(_ value: Value<T>) {
        observableValue = value
        self.value = observableValue.value
        observer = { [weak self] value in self?.value = value }
        observableValue.subscribe(observer: observer!)
    }
    
    deinit {
        observableValue.unsubscribe(observer: self.observer!)
    }
}
