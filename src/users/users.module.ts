import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { UsersService } from './users.service';
import { UsersController } from './users.controller';
import { User, UserSchema } from './schemas/user.schema';
import { IsUniqueEmail } from 'src/validators/is-unique-email.validator';

@Module({
  imports: [MongooseModule.forFeature([{ name: User.name, schema: UserSchema }])], // ✅ Import du modèle Mongoose
  controllers: [UsersController], // ✅ Déclaration du contrôleur
  providers: [UsersService, IsUniqueEmail], // ✅ Déclaration du service
  exports: [UsersService], // ✅ Exporte le service pour les autres modules
})
export class UsersModule {}
