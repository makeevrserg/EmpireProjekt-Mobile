//
//  SimpleChildStack.swift
//  iosApp
//
//  Created by Roman Makeev on 18.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Root

func simpleChildStack<T: AnyObject>(
    _ child: T
) -> Value<ChildStack<AnyObject, T>> {
    mutableValue(
        ChildStack(
            configuration: "config" as AnyObject,
            instance: child
        )
    )
}
