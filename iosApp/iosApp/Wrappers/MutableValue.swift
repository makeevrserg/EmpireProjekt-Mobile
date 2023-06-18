//
//  MutableValue.swift
//  iosApp
//
//  Created by Roman Makeev on 18.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Root

func mutableValue<T: AnyObject>(
    _ initialValue: T
) -> MutableValue<T> {
    MutableValueBuilderKt.MutableValue(
        initialValue: initialValue
    ) as! MutableValue<T>
}
