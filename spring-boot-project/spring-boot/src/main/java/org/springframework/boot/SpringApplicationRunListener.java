/*
 * Copyright 2012-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot;

import java.time.Duration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * Listener for the {@link SpringApplication} {@code run} method.
 * {@link SpringApplicationRunListener}s are loaded via the {@link SpringFactoriesLoader}
 * and should declare a public constructor that accepts a {@link SpringApplication}
 * instance and a {@code String[]} of arguments. A new
 * {@link SpringApplicationRunListener} instance will be created for each run.
 *
 * 抽象出Springboot启动各个生命周期的事件
 *
 * @author Phillip Webb
 * @author Dave Syer
 * @author Andy Wilkinson
 * @author Chris Bono
 * @since 1.0.0
 */
public interface SpringApplicationRunListener {

	/**
	 * Called immediately when the run method has first started. Can be used for very
	 * early initialization.
	 *
	 * 非常早的容器启动
	 *
	 * @param bootstrapContext the bootstrap context
	 */
	default void starting(ConfigurableBootstrapContext bootstrapContext) {
	}

	/**
	 * Called once the environment has been prepared, but before the
	 *
	 * 准备好环境（Environment构建完成），但在创建ApplicationContext之前调用。
	 *
	 * {@link ApplicationContext} has been created.
	 * @param bootstrapContext the bootstrap context
	 * @param environment the environment
	 */
	default void environmentPrepared(ConfigurableBootstrapContext bootstrapContext,
			ConfigurableEnvironment environment) {
	}

	/**
	 * Called once the {@link ApplicationContext} has been created and prepared, but
	 * before sources have been loaded.
	 *
	 * 在创建和构建ApplicationContext之后，但在加载之前调用。
	 *
	 * @param context the application context
	 */
	default void contextPrepared(ConfigurableApplicationContext context) {
	}

	/**
	 * Called once the application context has been loaded but before it has been
	 * refreshed.
	 *
	 * ApplicationContext已加载但在刷新之前调用。
	 *
	 * @param context the application context
	 */
	default void contextLoaded(ConfigurableApplicationContext context) {
	}

	/**
	 * The context has been refreshed and the application has started but
	 * {@link CommandLineRunner CommandLineRunners} and {@link ApplicationRunner
	 * ApplicationRunners} have not been called.
	 *
	 * ApplicationContext已刷新，应用程序已启动，但尚未调用CommandLineRunners和ApplicationRunners。
	 *
	 * @param context the application context.
	 * @param timeTaken the time taken to start the application or {@code null} if unknown
	 * @since 2.6.0
	 */
	default void started(ConfigurableApplicationContext context, Duration timeTaken) {
		started(context);
	}

	/**
	 * The context has been refreshed and the application has started but
	 * {@link CommandLineRunner CommandLineRunners} and {@link ApplicationRunner
	 * ApplicationRunners} have not been called.
	 * @param context the application context.
	 * @since 2.0.0
	 * @deprecated since 2.6.0 for removal in 2.8.0 in favor of
	 * {@link #started(ConfigurableApplicationContext, Duration)}
	 */
	@Deprecated
	default void started(ConfigurableApplicationContext context) {
	}

	/**
	 * Called immediately before the run method finishes, when the application context has
	 * been refreshed and all {@link CommandLineRunner CommandLineRunners} and
	 * {@link ApplicationRunner ApplicationRunners} have been called.
	 *
	 * 准备完毕
	 *
	 * @param context the application context.
	 * @param timeTaken the time taken for the application to be ready or {@code null} if
	 * unknown
	 * @since 2.6.0
	 */
	default void ready(ConfigurableApplicationContext context, Duration timeTaken) {
		running(context);
	}

	/**
	 * Called immediately before the run method finishes, when the application context has
	 * been refreshed and all {@link CommandLineRunner CommandLineRunners} and
	 * {@link ApplicationRunner ApplicationRunners} have been called.
	 *
	 * 在运行方法彻底完成之前立即调用，刷新ApplicationContext并调用所有CommandLineRunners和ApplicationRunner。
	 *
	 * @param context the application context.
	 * @since 2.0.0
	 * @deprecated since 2.6.0 for removal in 2.8.0 in favor of
	 * {@link #ready(ConfigurableApplicationContext, Duration)}
	 */
	@Deprecated
	default void running(ConfigurableApplicationContext context) {
	}

	/**
	 * Called when a failure occurs when running the application.
	 *
	 * 失败时调用
	 *
	 * @param context the application context or {@code null} if a failure occurred before
	 * the context was created
	 * @param exception the failure
	 * @since 2.0.0
	 */
	default void failed(ConfigurableApplicationContext context, Throwable exception) {
	}

}
