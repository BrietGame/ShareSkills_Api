import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { ValidationPipe } from '@nestjs/common';
import { IsUniqueEmail } from './validators/is-unique-email.validator';
import { UsersService } from './users/users.service';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // 💡 Récupérer `UsersService` et le passer au validateur
  const usersService = app.get(UsersService);
  IsUniqueEmail.setUsersService(usersService);

  app.useGlobalPipes(new ValidationPipe());
  await app.listen(process.env.PORT ?? 3000);
}
bootstrap();
