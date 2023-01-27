/*
 * MIT License
 *
 * Copyright (c) 2019 Alexander Kosarev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package name.alexkosarev.tutorial.note;

import name.alexkosarev.tutorial.note.web.NoteRestController;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

public class NoteBeansContextInitializer implements ApplicationContextInitializer<GenericApplicationContext> {

    @Override
    public void initialize(GenericApplicationContext applicationContext) {
        applicationContext.registerBean("noteService",
                NoteService.class,
                () -> new DefaultNoteService(applicationContext.getBean(NoteCrudRepository.class)));

        applicationContext.registerBean(
                // Bean name
                "noteRestController",
                // Bean type
                NoteRestController.class,
                // Bean supplier
                () -> new NoteRestController(applicationContext.getBean(NoteService.class)),
                // Bean definition customizers
                beanDefinition -> beanDefinition.setAutowireCandidate(false),
                beanDefinition -> beanDefinition.setDependsOn("noteService"));
    }
}
