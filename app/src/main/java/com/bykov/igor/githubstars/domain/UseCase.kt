package com.bykov.igor.githubstars.domain

import kotlinx.coroutines.experimental.Deferred
import org.kodein.di.KodeinAware

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 */
abstract class UseCase<T, in Params> : KodeinAware {

  internal abstract fun buildUseCaseObservable(params: Params): Deferred<T>
}
